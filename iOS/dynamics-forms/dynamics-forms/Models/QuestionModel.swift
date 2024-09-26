//
//  QuestionModel.swift
//  dynamics-forms
//
//  Created by Jonathas Matheus on 20/09/24.
//

/*
 Json:
 {
   "type": "text",
   "label": "First Name",
   "name": "first_name",
   "required": true,
   "uuid": "1e8562c5-1541-4c6f-aabb-000000000001"
 }
 */

import Foundation

class QuestionModel: Identifiable, Codable {
    var id: UUID = UUID() 
    var type: String
    var label: String
    var name: String
    var uuid: String
    var required: Bool?
    var options: [OptionModel]?

    init(type: String, label: String, name: String, uuid: String, required: Bool, options: [OptionModel]? = nil) {
         self.type = type
         self.label = label
         self.name = name
         self.uuid = uuid
         self.required = required
         self.options = options
     }
    
    
    enum CodingKeys: String, CodingKey {
        case type
        case label
        case name
        case uuid
        case required
        case options
    }
    
  
    
    
}
