//
//  Extensions.swift
//  iosApp
//
//  Created by Ahmet Furkan Sevim on 5.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

extension View {
    func placeholder<Content: View>(
        when shouldShow: Bool,
        alignment: Alignment = .leading,
        @ViewBuilder placeholder: () -> Content) -> some View {

        ZStack(alignment: alignment) {
            placeholder().opacity(shouldShow ? 1 : 0)
            self
        }
    }
}
