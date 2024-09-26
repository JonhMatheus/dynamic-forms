//
//  HttpUrlSeassion.swift
//  dynamics-forms
//
//  Created by Erickson Moura on 22/09/24.
//

import Foundation
import Combine

class HttpUrlSeassion: HttpInterface{
    
    func fetchAll(from url: URL) -> AnyPublisher<Data, Error> {
            URLSession
            .shared
            .dataTaskPublisher(for: url)
            .subscribe(on: DispatchQueue.global(qos: .default))
            .tryMap { output in
                guard let response = output.response as? HTTPURLResponse,
                      response.statusCode >= 200 else {
                    throw URLError(.badServerResponse)
                }
                return output.data
            }
            .eraseToAnyPublisher()
        }
    
    func post<T: Codable>(url: URL, body: T) -> AnyPublisher<Data, Error> {
          var request = URLRequest(url: url)
          request.httpMethod = "POST"
          request.addValue("application/json", forHTTPHeaderField: "Content-Type")
          
          do {
              let jsonData = try JSONEncoder().encode(body)
              request.httpBody = jsonData
          } catch {
              return Fail(error: error).eraseToAnyPublisher()
          }
          
          return URLSession.shared.dataTaskPublisher(for: request)
              .tryMap { output in
                  guard let response = output.response as? HTTPURLResponse, response.statusCode == 200 else {
                      throw URLError(.badServerResponse)
                  }
                  return output.data
              }
              .receive(on: RunLoop.main)
              .eraseToAnyPublisher()
      }

}

