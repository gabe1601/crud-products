import './App.css'
import ProductForm from "./components/home/ProductForm.jsx"
import ProductList from "./components/home/ProductList.jsx"

function App() {
  return(
    <div class="bg-linear-to-t from-sky-500 to-blue-900 text-white px-4 py-2 min-h-screen
      xl:text-base
      2xl:text-2xl" >
      <ProductList /> 
    </div>
  )


}

export default App
