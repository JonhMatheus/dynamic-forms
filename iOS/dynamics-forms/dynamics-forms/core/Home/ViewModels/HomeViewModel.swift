//
//  HomeViewModel.swift
//  dynamics-forms
//
//  Created by Jonathas Matheus on 19/09/24.
//

import Foundation
import Combine

class HomeViewModel: ObservableObject{
    
    @Published var allForms: [FormModel] = []
    @Published var responses = ResponseFormModel(title: "", responses: [])

    private var formService: FormDataService
    private var cancellables = Set<AnyCancellable>()
    
    
    init(formService: FormDataService = FormDataService(httpInterface: HttpUrlSeassion())) {
        self.formService = formService
        addForms()
    }
    
    func addForms(){
        formService.$allForms
            .sink { [weak self] (returnedForms) in
                self?.allForms = returnedForms
            }
            .store(in: &cancellables)
    }
    
    func submitResponse() {
        formService.postResponses(responses)
            .receive(on: DispatchQueue.main)
            .sink(receiveCompletion: { completion in
                
                switch completion {
                case .finished:
                    break
                case .failure(let error):
                    print(error)
                }
            }, receiveValue: { success in
                if success{
                  
                }
            })
            .store(in: &cancellables)
    }
}
