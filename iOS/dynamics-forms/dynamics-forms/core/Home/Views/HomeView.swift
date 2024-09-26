//
//  ContentView.swift
//  dynamics-forms
//
//  Created by Jonathas Matheus on 18/09/24.
//

import SwiftUI

struct HomeView: View {
    @StateObject private var viewModel: HomeViewModel

       init(formService: FormDataService = FormDataService(httpInterface: HttpUrlSeassion())) {
           _viewModel = StateObject(wrappedValue: HomeViewModel(formService: formService))
       }
    
    var body: some View {
        
        NavigationStack {
            ZStack {
                List {
                    ForEach(viewModel.allForms) { form in
                        FormListView(form: form)
                    }
                }
                .navigationTitle("📑 Forms")
                .toolbar{
                    ToolbarItem() {
                        NavigationLink(destination: CreateFormView(viewModel: CreateFormViewModel(formService: FormDataService(httpInterface: HttpUrlSeassion())))) {
                            Image(systemName: "plus")
                                .imageScale(.large)
                        }
                    }
                }
            }
        }
        .environmentObject(viewModel)
    }
}


struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
            .environmentObject(HomeViewModel())
    }
}


