//
//  SearchTextField.swift
//  iosApp
//
//  Created by Ahmet Furkan Sevim on 5.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct SearchTextField: View {
    @Binding var text: String
    var onSearch: () -> Void
    var onTextChange: (String) -> Void

    @FocusState private var isFocused: Bool
    
    var body: some View {
        ZStack(alignment: .leading) {
            // Hint text (placeholder)
            HStack {
                // Search icon
                Button(action: {
                    onSearch()
                }) {
                    Image(systemName: "magnifyingglass")
                        .foregroundColor(.gray)
                        .padding(.leading, 16)
                }
                
                // TextField
                TextField("", text: $text, onCommit: {
                    onSearch()  // Arama butonuna basılınca arama yap
                })
                .onChange(of: text) { newValue in
                    onTextChange(newValue)  // Query değiştiğinde çağır
                }
                .placeholder(when: text.isEmpty) {
                    Text("Search").foregroundColor(.gray)
                }.foregroundColor(.white)
                
                // Padding
                Spacer()
            }
            .padding(16)
            .background(Color(UIColor(red: 0.21, green: 0.2, blue: 0.23, alpha: 1)))
            .cornerRadius(5)
            .shadow(color: Color.black.opacity(0.2), radius: 2, x: 0, y: 2)
        }
        .padding()
    }
}
