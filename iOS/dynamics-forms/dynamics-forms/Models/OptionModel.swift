//
//  OptionModel.swift
//  dynamics-forms
//
//  Created by Jonathas Matheus on 21/09/24.
//

import Foundation

struct OptionModel: Identifiable, Codable{
    var id = UUID()
    var label: String
    var value: String
    
    init(label: String, value: String) {
        self.label = label
        self.value = value
    }
    
    enum CodingKeys: String, CodingKey {
        case label
        case value
    }
    
}
