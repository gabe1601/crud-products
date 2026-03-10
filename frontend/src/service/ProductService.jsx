export const loadProducts = async (pageNumber) => {
    const response = await fetch(`http://localhost:8080/products?page=${pageNumber}&size=10`);
    const data = await response.json();
    return data;
};

export const searchProduct = async (name) => {
    const response = await fetch(`http://localhost:8080/products/filter?name=${name}`);
    const data = await response.json();
    return data;
};