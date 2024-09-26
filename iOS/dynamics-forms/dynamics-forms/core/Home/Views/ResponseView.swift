//
//  ResponseView.swift
//  dynamics-forms
//
//  Created by Jonathas Matheus on 21/09/24.
//

import SwiftUI

struct ResponseView: View {
    var form: FormModel
    
    @StateObject private var viewModel: HomeViewModel

    @State private var fieldValues: [String: String] = [:]
    @State private var selectedOptions: [String: String] = [:]
    @Environment(\.dismiss) var dismiss

    
    init(formService: FormDataService = FormDataService(httpInterface: HttpUrlSeassion()),form: FormModel) {
        self.form = form
        _viewModel = StateObject(wrappedValue: HomeViewModel(formService: formService))
    }
    
    var body: some View {
        NavigationStack{
            Form{
                ForEach(form.fields) { question in
                    Section(header: Text(question.name)) {
                        switch question.type {
                        case "number":
                            TextField(question.label, text: Binding(
                                get: { fieldValues[question.name] ?? "" },
                                set: { fieldValues[question.name] = $0 }
                            ))
                            .keyboardType(.numberPad)
                            
                        case "description":
                            if let plainText = htmlToPlainText(html: question.label) {
                                Text(plainText)
                                    .background(Color.clear)
                                
                            } else {
                                Text("Erro ao converter o HTML.")
                                    .foregroundColor(.red)
                                
                            }
                            
                        case "dropdown":
                            Picker(question.label, selection: Binding(
                                get: { selectedOptions[question.name] ?? "" },
                                set: { selectedOptions[question.name] = $0 }
                            )) {
                                ForEach(question.options ?? []) { option in
                                    Text(option.label).tag(option.value)
                                }
                            }
                            .pickerStyle(MenuPickerStyle())
                            
                        default:
                            TextField(question.label, text: Binding(
                                get: { fieldValues[question.name] ?? "" },
                                set: { fieldValues[question.name] = $0 }
                            ))
                        }
                        
                    }
                }
                
                Button(action: {

                    let responses = form.fields.map { field in
                            ResponseModel(fieldName: field.name, value: fieldValues[field.name] ?? selectedOptions[field.name] ?? "")
                        }
                        
                        let responseForm = ResponseFormModel(title: form.title, responses: responses)
                    viewModel.responses = responseForm
                    viewModel.submitResponse()
                    dismiss()
            
                }) {
                    Text("Enviar")
                        .fontWeight(.bold)
                        .frame(maxWidth: .infinity)
                        .padding()
                        .background(Color.blue)
                        .foregroundColor(.white)
                        .cornerRadius(8)
                }
                .background(Color.clear)
                .listRowInsets(EdgeInsets())
            }
            .navigationTitle(form.title)
            
        }.environmentObject(viewModel)
    }
    
    func htmlToPlainText(html: String) -> String? {
        guard let data = html.data(using: .utf8) else { return nil }
        
        do {
            let attributedString = try NSAttributedString(
                data: data,
                options: [
                    .documentType: NSAttributedString.DocumentType.html,
                    .characterEncoding: String.Encoding.utf8.rawValue],
                documentAttributes: nil)
            return attributedString.string
        } catch{
            print("Erro ao converter HTML para texto: \(error)")
            return nil
        }
    }
}


//struct ResponseView_Previews: PreviewProvider {
//    static var previews: some View {
//        ResponseView(firstName: "Teste")
//    }
//}
