//
//  FormListView.swift
//  dynamics-forms
//
//  Created by Jonathas Matheus on 19/09/24.
//

import SwiftUI

struct FormListView: View {
    var form: FormModel
    
    var body: some View {
        NavigationLink(destination: ResponseView(form: form)){
            VStack(alignment: .leading , spacing: 10){
                HStack{
                    Image(systemName: "doc")
                        .foregroundStyle(.black)
                    Text(form.title)
                        .foregroundStyle(.black)
                        .bold()
                    
                }
                Text(form.id.uuidString)
                    .font(.caption2)
                    .foregroundStyle(.gray).fontWeight(.light)
                    .padding(.bottom, 6)
                
                HStack{
                    Text("\(form.fields.count) fields")
                        .font(.caption)
                        .foregroundStyle(.white)
                        .padding(8)
                        .background(.indigo.opacity(0.8))
                        .bold()
                        .clipShape(RoundedRectangle(cornerRadius: 8))
                    
                    
                    Text("20 submissions")
                        .font(.caption)
                        .foregroundStyle(.white)
                        .padding(8)
                        .background(.blue.opacity(0.8))
                        .bold()
                        .clipShape(RoundedRectangle(cornerRadius: 8))
                }
                
            }
            .padding(8)
        }
        
    }
    
}
