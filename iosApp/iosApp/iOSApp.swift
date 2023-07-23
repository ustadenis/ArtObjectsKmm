import SwiftUI
import shared

@main
struct iOSApp: App {
    init() {
        AppKt.doInitKoin()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
