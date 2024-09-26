//
//  FormModel.swift
//  dynamics-forms
//
//  Created by Jonathas Matheus on 22/09/24.
//

import Foundation

struct FormResponse: Codable {
    var forms: [FormModel]
}

struct FormModel: Identifiable, Codable{
    var id = UUID()
    var title: String
    var fields: [QuestionModel]
   
    
    init(title: String, fields: [QuestionModel]) {
        self.title = title
        self.fields = fields
    }
    
     enum CodingKeys: String, CodingKey {
         case title
         case fields
     }
}
