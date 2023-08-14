# travelExample
use MVVM by Kotlin

## 基本
- 使用Kotlin
- 使用Fragment
- 程式架構MVVM

## 必要
- 多語系切換，並非使用正規方法切換，而是使用寫死於程式中的陣列。
- 首頁清單景點照片，於首頁中採用glide第三方庫呈現
- 單一景點顯示全部景點照片，其內容中使用github第三方庫ImageSlideshow呈現
<a href="https://github.com/denzcoskun/ImageSlideshow/"><b>ImageSlideshow</b></a>
```xml
  優點: 易於使用
  缺點: 庫本身並不是MVVM
```
- webView 開啟 url，僅採用較簡易的方式呈現
```xml
  左上角返回箭頭，若能返回webview上一頁則返回，若不行則返回前一頁的fragment
  webview title顯示網址
  webview瀏覽至其他頁面時不會跳轉到瀏覽器，仍在本app內
```
