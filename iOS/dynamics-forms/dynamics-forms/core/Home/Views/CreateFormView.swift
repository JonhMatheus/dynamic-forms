//
//  FormView.swift
//  dynamics-forms
//
//  Created by Jonathas Matheus on 19/09/24.
//

import SwiftUI

struct CreateFormView: View {
    @StateObject var viewModel: CreateFormViewModel

    @State private var questions:[QuestionModel] = []
    
    @State private var isNewQuestion: Bool = true
    @State private var labelTextField: String = ""
    @State private var selectedType: String = ""
    @State private var titleForm: String = ""
    @State private var showAlert: Bool = false

    
    @Environment(\.presentationMode) var presentationMode
    
    var body: some View {
        
        ScrollView {
            VStack(alignment: .leading) {
                TextField("Form name", text: $titleForm)
                    .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/)
                    .foregroundStyle(.black)
                    .font(.title)
                
                Rectangle()
                    .frame(height: 0.5)
                    .foregroundColor(.gray)
                
                Text("Give a name to your custom form").font(.caption).fontWeight(.thin).foregroundStyle(.gray)
                
                Spacer().frame(height: 30)
                
                ForEach($questions) { $question in
                    NewFormComponent(newQuestion: $question)
                }
                if isNewQuestion {
                    Button(action: {
                        addQuestion()
                    }) {
                        HStack {
                            Image(systemName: "plus")
                            Text("add field")
                        }
                        .padding(10)
                        .background(Color.blue)
                        .foregroundStyle(Color.white)
                        .clipShape(RoundedRectangle(cornerRadius: 16))
                    }
                }
                Spacer().frame(height: 30)
                if !questions.isEmpty{
                    Button(action: {
                        viewModel.form.title = titleForm
                        viewModel.form.fields = questions
                        viewModel.submitForm()
                        print("Botão pressionado")
                    }) {
                        if viewModel.isSubmitting {
                            ProgressView()
                        } else {
                            Text("Enviar")
                                .fontWeight(.bold)
                                .frame(maxWidth: .infinity)
                                .padding()
                                .background(Color.blue)
                                .foregroundColor(.white)
                                .cornerRadius(8)
                        }
                        
                    }
                    .alert(isPresented: $viewModel.showConfirmation) {
                        Alert(
                            title: Text("Form Created"),
                            message: Text("The form was created successfully."),
                            dismissButton: .default(Text("OK")) {
                                
                                presentationMode.wrappedValue.dismiss()
                            }
                        )
                    }
                }
            }
            .padding(25)
        }
        
    }
    
    
    func addQuestion() {
        let newQ =  QuestionModel(
            type: "",
            label: "",
            name: "",
            uuid: UUID().uuidString,
            required: false)
        
        questions.append(newQ)
    }
}

