//
//  CustomViewForm.swift
//  dynamics-forms
//
//  Created by Erickson Moura on 19/09/24.
//

import SwiftUI

    struct CustomViewForm: View {
        var titleForm: String
        var dateCreated: String
       
        var body: some View {
            Text(titleForm)
                .fontWeight(.medium)
            SizedBox(heigth: 8)
            Text(dateCreated)
                .font(.caption)
                .fontWeight(.light)
        }
    }

