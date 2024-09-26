//
//  ResponseModel.swift
//  dynamics-forms
//
//  Created by Jonathas Matheus on 22/09/24.
//

import Foundation

struct ResponseModel: Codable, Identifiable {
    var id = UUID()
    let fieldName: String
    let value: String
}
