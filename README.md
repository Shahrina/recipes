# 🍽️ Recipes

A lightweight Android app for browsing and viewing recipes using Jetpack Compose and Retrofit.  
Data is sourced from the open [SampleAPIs Recipes API](https://api.sampleapis.com/recipes/recipes).

---

## 🧱 Architecture

- **MVVM**: ViewModel manages UI state
- **Jetpack Compose**: Stateless, modular UI components
- **Retrofit + Moshi** for API
- **Manual DI**: No Hilt (for simplicity)
- **Navigation Compose** for screen transitions

---

## ⚖️ Trade-offs & Known Limitations

- ❌ No pagination — API doesn’t support it
- ❌ No local caching — fresh fetch every launch
- ❌ No Hilt — fewer layers, but less scalable
- ❌ Search is done client-side
- 🔐 Error handling is basic (no status-specific feedback)

---

## ✨ Nice-to-Haves (Not Yet Implemented)

- ❌ Offline support via Room
- ❌ Favorites and cuisine filtering
- ❌ Pull-to-refresh
- ❌ UI tests and Compose previews
- ❌ Improved error state granularity (e.g. 404 vs network failure)

---

## ✅ Next Steps

- Add Hilt for dependency injection
- Cache recipes locally with Room
- Implement favorites and filters
- Add UI polish and test coverage
