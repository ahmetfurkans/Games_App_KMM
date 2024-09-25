//
//  GamesScreen.swift
//  iosApp
//
//  Created by Ahmet Furkan Sevim on 28.08.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

extension GamesScreen {
    @MainActor
    class GamesViewModelWrapper: ObservableObject {
        let gamesViewModel: GamesViewModel
        @Published var query: String
        @Published var selectedIndex: Int32
        @Published var platforms: [Platform]
        
        init() {
            gamesViewModel = GamesInjector().gamesViewModel
            gamesState = gamesViewModel.gamesState.value
            query = gamesViewModel.gamesState.value.query
            platforms = gamesViewModel.gamesState.value.platforms
            selectedIndex = gamesViewModel.gamesState.value.selectedPlatformIndex
        }
        
        @Published var gamesState: GamesState
        
        func startObserving() {
            Task {
                for await gamesS in gamesViewModel.gamesState {
                    self.gamesState = gamesS
                    self.query = gamesS.query // Query'yi de güncelle
                    self.platforms = gamesS.platforms
                    self.selectedIndex = gamesS.selectedPlatformIndex
                }
            }
        }
        
        func onQueryChange(newQuery: String) {
            query = newQuery
            gamesViewModel.onQueryChange(query: newQuery)
        }
        
        func onPlatformChange(selectedIndex: Int32){
            gamesViewModel.onSelectTab(platformIndex: selectedIndex)
        }
        
        func onSearchClick() {
            gamesViewModel.onSearchClick()
        }
        
    }}

struct GamesScreen: View {
    
    @ObservedObject private(set) var viewModel: GamesViewModelWrapper
    
    var body: some View {
        VStack () {
            
            SearchTextField(text:$viewModel.query, onSearch: {viewModel.onSearchClick()}, onTextChange: viewModel.onQueryChange)
            if(!viewModel.gamesState.platforms.isEmpty) {
                PlatformsTabRow(platforms:  $viewModel.platforms, selectedIndex: $viewModel.selectedIndex ,onPlatformSelect:viewModel.onPlatformChange)
            }
            Spacer()
            
            if viewModel.gamesState.loading {
                Loader()
            }
            
            if let error = viewModel.gamesState.error {
                ErrorMessage(message: error)
            }
            
            if(!viewModel.gamesState.games.isEmpty) {
                ScrollView {
                    let columns = [
                        GridItem(.flexible()),
                        GridItem(.flexible())
                    ]
                    LazyVGrid(columns: columns, spacing: 16) {
                        ForEach(viewModel.gamesState.games, id: \.self) { game in
                            NavigationLink(destination: GameDetailScreen(viewModel: .init(), uid: game.uid)){
                                GameItemView(game: game)
                            }
                        }
                    }
                }
            }
        }.onAppear{
            self.viewModel.startObserving()
        }.frame(maxHeight: .infinity).background(secondary)
    }
}

struct GameItemView: View {
    let game: Game
    
    var body: some View {
        VStack(alignment: .leading) {
            AsyncImage(url: URL(string: game.backgroundImg)) {
                image in
                image
                    .resizable()
                    .scaledToFill()
                    .frame(minWidth: 0, maxWidth: .infinity)
                    .aspectRatio(1, contentMode: .fill)
                    .clipped()
            } placeholder: {
                Color.gray
                    .frame(minWidth: 0, maxWidth: .infinity)
            }.scaledToFit()
            Text(game.name)
                .font(.system(size: 16))
                .fontWeight(.regular)
                .foregroundStyle(.white)
                .frame(maxWidth: .infinity, alignment: .leading)
                .lineLimit(2, reservesSpace: true)
                .padding(8)
        }
        .frame(alignment: .leading)
        .background(primary)
        .cornerRadius(8)
        .padding(16)
    }
}

struct Loader: View {
    var body: some View {
        ProgressView()
    }
}

struct ErrorMessage: View {
    var message: String
    
    var body: some View {
        Text(message)
            .font(.title)
    }
}

