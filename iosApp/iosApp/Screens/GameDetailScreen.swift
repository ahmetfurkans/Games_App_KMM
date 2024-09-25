//
//  GameDetailScreen.swift
//  iosApp
//
//  Created by Ahmet Furkan Sevim on 9.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

extension GameDetailScreen {
    
    @MainActor
    class GameDetailViewModelWrapper: ObservableObject {
        let gameDetailViewModel: GameDetailViewModel
        @Published var gameState: GameDetailState
        
        init() {
            gameDetailViewModel = GamesInjector().gameDetailViewModel
            gameState = gameDetailViewModel.gameState.value
        }
        
        func startObserving() {
            Task {
                for await gameS in gameDetailViewModel.gameState {
                    self.gameState = gameS
                }
            }
        }
        deinit{
            gameDetailViewModel.dispose()
        }
    }
}

struct GameDetailScreen: View {
    @ObservedObject private(set) var viewModel: GameDetailViewModelWrapper
    var uid: Int32
    
    init(viewModel: GameDetailViewModelWrapper, uid: Int32) {
        self.viewModel = viewModel
        self.uid = uid
        viewModel.gameDetailViewModel.getGameDetail(uid: uid)
    }
    
    var body: some View {
        VStack{
            if viewModel.gameState.loading {
                Loader()
            }
            
            if let error = viewModel.gameState.error {
                ErrorMessage(message: error)
            }
            
            if let game = viewModel.gameState.game{
                GameCard(gameDetail: viewModel.gameState.game!)
            }
            
            Spacer()
            
        }.onAppear{
            self.viewModel.startObserving()
        }.frame(maxWidth: .infinity, maxHeight: .infinity).background(secondary)
    }
}

struct GameCard: View {
    let gameDetail: GameDetail
    @State private var isExpanded: Bool = false
        
    var body: some View {
        ScrollView{
            VStack {
                AsyncImage(url: URL(string: gameDetail.backgroundImg)) {
                    image in
                    image
                        .resizable()
                        .scaledToFill()
                        .frame(minWidth: 0, maxWidth: .infinity, maxHeight: 200)
                        .aspectRatio(1, contentMode: .fill)
                        .clipped()
                } placeholder: {
                    Color.gray
                        .frame(minWidth: 0, maxWidth: .infinity)
                }.scaledToFit()
                VStack {
                    HStack {
                        Text(gameDetail.name)
                            .fontWeight(.medium)
                            .foregroundStyle(.white)
                            .lineLimit(1)
                            .font(.headline)
                        Spacer()
                        if let metacritic = gameDetail.metacritic {
                            Text(metacritic)
                                .font(.caption)
                                .padding(.horizontal, 8)
                                .padding(.vertical, 4)   
                                .foregroundColor(.white)
                                .cornerRadius(8) // Köşeleri yuvarla
                                .overlay(
                                    RoundedRectangle(cornerRadius: 8)
                                        .stroke(.gray, lineWidth: 1) // 1px siyah çerçeve
                                )
                        }
                    }
                    VStack(alignment: .leading, spacing: 10) {
                        Text("Description")
                            .fontWeight(.medium)
                            .foregroundStyle(.white)
                            .font(.headline)
                        Text(gameDetail.description)
                            .fontWeight(.medium)
                            .foregroundStyle(.gray)
                            .lineLimit(isExpanded ? nil : 4) // Genişletilmişse tüm metin, değilse 4 satır
                            .animation(.easeInOut, value: isExpanded) // Animasyonlu geçiş
                            .onTapGesture {
                                isExpanded.toggle() // Tıklandığında expand/collapse
                            }
                            .foregroundColor(.primary)
                    }
                    .padding()
                    .cornerRadius(16)
                    .background(primary)
                    .onTapGesture {
                        isExpanded.toggle()
                    }
                    VStack(alignment: .leading, spacing: 10) {
                        Text("Information")
                            .fontWeight(.medium)
                            .foregroundStyle(.white)
                            .font(.headline)
                        if let released = gameDetail.released {
                                HStack {
                                    Text("Release Date")
                                        .fontWeight(.medium)
                                        .foregroundStyle(.gray)
                                        .font(.headline)
                                    Spacer()
                                    Text(gameDetail.released!)
                                        .fontWeight(.medium)
                                        .foregroundStyle(.white)
                                        .font(.headline)
                                }
                        }
                        if(!gameDetail.genres.isEmpty) {
                            let genresText = gameDetail.genres.joined(separator: ", ")
                            HStack {
                                Text("Genres")
                                    .fontWeight(.medium)
                                    .foregroundStyle(.gray)
                                    .font(.headline)
                                Spacer()
                                Text(genresText)
                                    .fontWeight(.medium)
                                    .foregroundStyle(.white)
                                    .font(.headline)
                            }
                        }
                        if let released = gameDetail.playtime {
                            HStack {
                                Text("Play Time")
                                    .fontWeight(.medium)
                                    .foregroundStyle(.gray)
                                    .font(.headline)
                                Spacer()
                                Text(gameDetail.playtime!)
                                    .fontWeight(.medium)
                                    .foregroundStyle(.white)
                                    .font(.headline)
                            }
                        }
                        if(!gameDetail.publishers.isEmpty) {
                            let publishersText = gameDetail.publishers.joined(separator: ", ")
                            HStack {
                                Text("Publishers")
                                    .fontWeight(.medium)
                                    .foregroundStyle(.gray)
                                    .font(.headline)
                                Spacer()
                                Text(publishersText)
                                    .lineLimit(1)
                                    .fontWeight(.medium)
                                    .foregroundStyle(.white)
                                    .font(.headline)
                            }
                        }
                    }
                    .frame(maxWidth: .infinity)
                    .padding()
                    .cornerRadius(16)
                    .background(primary)
                    if let redditLink = gameDetail.redditUrl {
                        Button(action: {
                            if let url = URL(string: redditLink) {
                                UIApplication.shared.open(url)
                            }
                        }) {
                            HStack {
                                Text("Visit Reddit")
                                    .font(.headline)
                                Spacer()
                                Image(systemName: "chevron.right")
                            }
                            .padding()
                            .background(primary)
                            .cornerRadius(10)
                            .foregroundColor(.gray)
                        }
                    }
                    if let websiteLink = gameDetail.webSiteUrl {
                        Button(action: {
                            if let url = URL(string: websiteLink) {
                                UIApplication.shared.open(url)
                            }
                        }) {
                            HStack {
                                Text("Visit Website")
                                    .font(.headline)
                                Spacer()
                                Image(systemName: "chevron.right")
                            }
                            .padding()
                            .background(primary)
                            .cornerRadius(10)
                            .foregroundColor(.gray)
                        }
                    }
                }
                .padding(16)
            }
        }
    }
}
