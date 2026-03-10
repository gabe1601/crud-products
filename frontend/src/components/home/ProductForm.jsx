import { useState } from "react";
import { useEffect } from "react";
import { loadProducts } from "../../service/ProductService";

function ProductForm({onProductSaved}){

    const [name, setName] = useState("");
    const [price, setPrice] = useState("");
    const [quantity, setQuantity] = useState("");

    const handleSubmit = async () => {

        const product = {
            name: name,
            price: price,
            quantity: quantity
        };

        const response = await fetch("http://localhost:8080/products",{
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(product)
        });
        if (response.ok){
            alert ("Salvo com sucesso!");

            setName ("");
            setPrice ("");
            setQuantity ("");

            onProductSaved();
        }
    };

    return( 
        <div className="">
            <h2 className="text-4xl font-bold">CADASTRO DE PRODUTOS</h2>

            <div className="flex flex-row  items-center">
                <div className="p-8 grid grid-cols-3 gap-10
                    w-full
                    mas-w-[600px]
                    lg:max-w[900px]
                    xl:max-w[1200px]
                    2xl:max-w-[1400px]
                    lg:h-[90px]
                    xl:h-[110px]
                    ">
                    <input className="border rounded-md p-1 shadow-md focus:outline-none"
                        text="text"
                        placeholder="Nome Produto"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                    />

                    <input
                        className="border rounded-md p-1 shadow-md focus:outline-none"
                        text="number"
                        placeholder="Valor Produto"
                        value={price}
                        onChange={(e) => setPrice(e.target.value)}
                    />

                    <input
                        className="border rounded-md p-1 shadow-md focus:outline-none"
                        text="number"
                        placeholder="Quantidade de estoque"
                        value={quantity}
                        onChange={(e) => setQuantity(e.target.value)}
                    />
                </div>
                <div className="grid gap-2">
                    <button onClick={handleSubmit} className="bg-sky-500 hover:bg-sky-700 border rounded-md h-10 w-30 shadow-md cursor-pointer">
                        Cadastrar
                    </button>
                </div>

            </div>            
            
        </div>
    )
}

export default ProductForm;