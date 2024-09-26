//
//  CreateFormViewModel.swift
//  dynamics-forms
//
//  Created by Jonathas Matheus on 23/09/24.
//

import Foundation
import Combine

class CreateFormViewModel: ObservableObject{
    @Published var form = FormModel(title: "", fields: [])
    @Published var submissionSuccess = false
    @Published var errorMessage: String?
    @Published var isSubmitting: Bool = false
    @Published var showConfirmation: Bool = false

    let formService: FormDataService
    private var cancellables = Set<AnyCancellable>()

    
    init(formService: FormDataService) {
           self.formService = formService
       }
    
    func submitForm() {
        isSubmitting = true
        
        formService.postForm(form)
            .receive(on: DispatchQueue.main)
            .sink(receiveCompletion: { completion in
                self.isSubmitting = false
                
                switch completion {
                case .finished:
                    break
                case .failure(let error):
                    self.errorMessage = error.localizedDescription
                }
            }, receiveValue: { success in
                if success{
                    self.submissionSuccess = success
                    self.showConfirmation = true
                }
            })
            .store(in: &cancellables)
    }
}
