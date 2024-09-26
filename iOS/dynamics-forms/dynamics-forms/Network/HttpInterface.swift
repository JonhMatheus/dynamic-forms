//
//  HttpInterface.swift
//  dynamics-forms
//
//  Created by Jonathas Matheus on 22/09/24.
//

import Foundation
import Combine

protocol HttpInterface {
    func fetchAll(from url: URL) -> AnyPublisher<Data, Error>
    func post<T: Codable>(url: URL, body: T) -> AnyPublisher<Data, Error>
}
