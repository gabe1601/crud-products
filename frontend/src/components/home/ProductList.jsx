import { useEffect, useState } from "react";
import { loadProducts, searchProduct } from "../../service/ProductService";
import ProductForm from "./ProductForm";


function ProductList(){

    const [products, setProducts] = useState([]);
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [search, setSearch] = useState("");
    

    const fetchProducts = async(pageNumber) => {

        const data = await loadProducts(pageNumber);

        setProducts(data.content);
        setTotalPages(data.totalPages);
        setPage(data.number)
    };

    
    useEffect(()=>{
        fetchProducts(page);
    },[page]);

    const deleteProduct = async(id) => {

        const response = await fetch (`http://localhost:8080/products/${id}`,{
            method : "DELETE"
        });
        if(response.ok){
            alert("Produto excluido com sucesso!");
            fetchProducts(page);
        }
    }

    const handleSearch = async(name) => {
        if(!name.trim()){
            fetchProducts(0);
            return
        }

        const data = await searchProduct(name);
        setProducts(data);
    }

    const editProduct = async(product) => {

        const newName = prompt ("Novo nome: " , product.name)
        const newPrice =  prompt ("Novo preço: " , product.price)
        
        if(!newName || !newPrice) return;

        const updateProduct = {
            name: newName,
            price : newPrice
        };

        const response = await fetch (`http://localhost:8080/products/${product.id}`,{
            method: "PUT",
            headers: {
                "content-type" : "application/json"
            },
            body: JSON.stringify(updateProduct)
        });

        if(response.ok){
            alert("Produto Atualizaro");
            fetchProducts();
        }
        
    }

    return(
        <>
            <ProductForm onProductSaved={() => fetchProducts(page)} />

        <div className="py-5">
            <h2 className="text-4xl font-bold">Produtos Cadastrados no Sistema</h2>


            <div className="flex gap-5 my-5">
                <h1>Procurar Produto</h1>
                <input
                        className="border rounded-md p-1 shadow-md focus:outline-none"
                        type="text"
                        placeholder="Nome"
                        value={search}
                        onChange={(e) => setSearch(e.target.value)}
                    />
                <button onClick={()=> handleSearch(search)} className="bg-sky-500 hover:bg-sky-700 border rounded-md h-10 w-30 shadow-md cursor-pointer">
                    Procurar
                </button>
            </div>



            <div className="px-10 py-4 h-auto">
                {products.length === 0 ? (
                    <p>Nenhum produto encontrado</p>
                ) : (
                <table className="
                    border-collapse border border-sky-400  text-center bg-sky-700 text-sky-100 w-full">
                        <thead>
                            <tr className="bg-sky-800 text-sky-100 h-[40px] ">
                                <th className="border border-blue-400">Id</th>
                                <th className="border border-blue-400">Produto</th>
                                <th className="border border-blue-400">Preço</th>
                                <th className="border border-blue-400">Quantidade</th>
                                <th className="border border-blue-400">Ação</th>
                            </tr>
                        </thead>
                        <tbody>
                        {products.map((product) => (
                            
                            <tr key={product.id}>
                                <td className="border border-blue-400">{product.id}</td>
                                <td className="border border-blue-400">{product.name}</td>
                                <td className="border border-blue-400">R$ {Number(product.price).toFixed(2)}</td>
                                <td className="border border-blue-400">{product.quantity}</td>

                                <td className="border border-blue-400 flex w-full h-full font-bold text-white">
                                    <button onClick={()=> deleteProduct(product.id)} className="bg-red-500 hover:bg-red-600 w-full h-full cursor-pointer">
                                        Deletar
                                    </button>
                                    <button onClick={()=> editProduct(product)}className="bg-gray-700 hover:bg-gray-800 w-full h-full cursor-pointer">
                                        Editar
                                    </button>
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>

                )}
                <div className="flex justify-between py-4
                    w-full
                    lg1:max-w-[900px]
                    xl:max-w-[1750px]">
                    <button
                        className="bg-sky-200 hover:bg-sky-700 text-blue-900 hover:text-blue-100 border rounded-md h-10 w-50 cursor-pointer"
                        disabled = {page === 0}
                        onClick={()=>fetchProducts(page - 1)}
                    >
                        Página anterior
                    </button>

                    <span>Página {page + 1} de {totalPages}</span>
                    
                    <button
                        className="bg-sky-200 hover:bg-sky-700 text-blue-900 hover:text-blue-100 border rounded-md h-10 w-50 cursor-pointer"
                        disabled={page + 1 === totalPages}
                        onClick={()=> fetchProducts(page + 1)}
                    >
                        Próxima página
                    </button>
                </div>

            </div>
            


        </div>
                </>
    );
}

export default ProductList;