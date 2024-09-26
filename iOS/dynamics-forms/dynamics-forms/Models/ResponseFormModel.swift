//
//  ResponseFormModel.swift
//  dynamics-forms
//
//  Created by Jonathas Matheus on 22/09/24.
//

import Foundation

struct ResponseFormModel: Codable {
    let title: String
    let responses: [ResponseModel]
}
