//
//  PlatformsTabRow.swift
//  iosApp
//
//  Created by Ahmet Furkan Sevim on 6.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct PlatformsTabRow: View {
    @Binding var platforms: [Platform]
    @Binding var selectedIndex: Int32
    var onPlatformSelect: (Int32) -> Void
    
    var body: some View {
        ScrollViewReader { scrollView in
            ScrollView(.horizontal, showsIndicators: false) {
                HStack {
                    ForEach(platforms.indices, id: \.self) { index in
                        
                        VStack{
                            Text(platforms[index].name)
                                .font(.subheadline)
                                .padding(.horizontal)
                                .menuIndicator(.visible)
                                .padding(.vertical, 4)
                                .foregroundColor(selectedIndex == Int32(index) ? tertiary : Color(red: 0.21, green: 0.2, blue: 0.23))
                                .onTapGesture {
                                    withAnimation(.easeInOut) {
                                        if (selectedIndex == index) {
                                            onPlatformSelect(Int32(-1))
                                        } else {
                                            onPlatformSelect(Int32(index))
                                        }
                                    }
                                }
                            Rectangle()
                                .frame(height: 1) // 1px high line
                                .foregroundColor(selectedIndex == Int32(index) ? tertiary : .clear
                                )
                        }
                    }
                }
            }
        }.padding(.horizontal, 16)
    }
}

