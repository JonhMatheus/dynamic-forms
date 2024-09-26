//
//  NewFormComponent.swift
//  dynamics-forms
//
//  Created by Jonathas Matheus on 20/09/24.
//

import SwiftUI

struct NewFormComponent: View {
    @Binding var newQuestion: QuestionModel
    @State private var isRequired: Bool = false
    
    var body: some View {
        Section(
            header: Text("Name")
                .font(.subheadline)){
            TextField("Type name (Ex: email)", text: $newQuestion.name)
                .padding()
                .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/)
                .background(Color.gray.opacity(0.1))
                .foregroundStyle(.black)
                .clipShape(.rect(cornerRadius: 8))
        }.padding(.bottom, 5)
        Section(
            header: Text("Label")
                .font(.subheadline)
            
        ){
            TextField("Type the label", text: $newQuestion.label)
                .padding()
                .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/)
                .background(Color.gray.opacity(0.1))
                .foregroundStyle(.black)
                .clipShape(.rect(cornerRadius: 8))
        }
        if newQuestion.required != nil {
            Button(action: {
                isRequired.toggle()
                    newQuestion.required!.toggle()
                
            }) {
                HStack {
                    Image(systemName: isRequired ? "checkmark.square" : "square")
                        .foregroundColor(isRequired ? .blue : .gray)
                    Text("Required")
                }
            }
            .buttonStyle(PlainButtonStyle())
            .padding(.vertical, 10)
            
            Picker("Tipo de Pergunta", selection: $newQuestion.type) {
                Text("Text").tag("text")
                Text("Number").tag("number")
                Text("Dropdown").tag("dropdown")
                Text("Description").tag("description")
            }
            .pickerStyle(SegmentedPickerStyle())
            .padding(.bottom, 15)

        }
        
        Divider().padding(.vertical, 10)
        
    }
        

     
}

