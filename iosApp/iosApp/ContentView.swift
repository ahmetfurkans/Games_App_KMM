import SwiftUI
import shared

struct ContentView: View {
    
    init() {
        let appearance = UITabBarAppearance()
                    appearance.configureWithOpaqueBackground()
                    appearance.backgroundColor = .black
        
                    appearance.stackedLayoutAppearance.selected.iconColor = UIColor(tertiary)
                    appearance.stackedLayoutAppearance.selected.titleTextAttributes = [.foregroundColor: UIColor(tertiary)]
                    appearance.stackedLayoutAppearance.normal.iconColor = UIColor.white
                    appearance.stackedLayoutAppearance.normal.titleTextAttributes = [.foregroundColor: UIColor.white]
               
        UITabBar.appearance().scrollEdgeAppearance = appearance
        UITabBar.appearance().standardAppearance = appearance
    }
    
    var body: some View {
        let gamesScreen = GamesScreen(viewModel: .init())
        
        NavigationStack {
            TabView {
                gamesScreen
                    .tabItem {
                        Label("Menu", systemImage: "list.dash")
                    }
                WishGamesScreen()
                    .tabItem {
                        Label("asdfasdf", systemImage: "list.dash")
                    }
            }
        }
    }
}

