//
//  FormDataService.swift
//  dynamics-forms
//
//  Created by Jonathas Matheus on 22/09/24.
//

import Foundation
import Combine

class FormDataService{
    
    private var httpInterface: HttpInterface
    @Published var allForms: [FormModel] = []
    
    init(httpInterface: HttpInterface) {
        self.httpInterface = httpInterface
        getForms()
    }
    
    func getForms() {
           guard let url = URL(string: "https://dynamic-forms-b4f58-default-rtdb.firebaseio.com/forms/.json") else { return }
           
           httpInterface.fetchAll(from: url)
               .receive(on: DispatchQueue.main)
               .decode(type: [String: FormModel].self, decoder: JSONDecoder())
               .sink { completion in
                   switch completion {
                   case .finished:
                       break
                   case .failure(let error):
                       print("Error: \(error)")
                   }
               } receiveValue: { [weak self] formsDictionary in
                   let forms = formsDictionary.map { (key, value) in
                       FormModel(title: value.title, fields: value.fields)
                   }
                   self?.allForms = forms
               }
               
       }
    
    func postForm(_ form: FormModel) -> AnyPublisher<Bool, Error> {
          guard let url = URL(string: "https://dynamic-forms-b4f58-default-rtdb.firebaseio.com/forms/.json") else {
              return Fail(error: URLError(.badURL)).eraseToAnyPublisher()
          }
          
          return httpInterface.post(url: url, body: form)
              .map { _ in true } 
              .eraseToAnyPublisher()
      }
    
    func postResponses(_ response: ResponseFormModel) -> AnyPublisher<Bool, Error> {
          guard let url = URL(string: "https://dynamic-forms-b4f58-default-rtdb.firebaseio.com/responses/.json") else {
              return Fail(error: URLError(.badURL)).eraseToAnyPublisher()
          }
          
          return httpInterface.post(url: url, body: response)
              .map { _ in true }
              .eraseToAnyPublisher()
      }
    
}
