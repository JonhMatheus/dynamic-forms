//
//  SizedBox.swift
//  dynamics-forms
//
//  Created by Jonathas Matheus on 19/09/24.
//

import SwiftUI

struct SizedBox: View {
    var heigth: Double?
    var width: Double?
    
    var body: some View {
            Spacer()
                .frame(width: width ?? 0, height: heigth ?? 0)
        
    }
}

