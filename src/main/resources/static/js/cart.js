/**
 * @type {NodeList}
 */
const addToCartButtons = document.querySelectorAll(".add-button");

/**
 * @type {number}
 */
let total = 0;


addToCartButtons.forEach(addToCartButton => { 
    addToCartButton.addEventListener('click', addItemToCart) 
}
)

window.addEventListener("DOMContentLoaded", function (e) {
  changeBadge();
  const itemsNumberEl = document.getElementById('items-number');
  

  if(itemsNumberEl) {
    itemsNumberEl.textContent = `${cartSize()} items`;
    updateCartListUI();
    displayTotal();

    const params = new URLSearchParams(this.window.location.search);
    // console.log(params.keys().next().value);

    const toastLiveExample = document.getElementById('liveToast')
    if(toastLiveExample){

        const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample);
    
        toastBootstrap.show();
    }

    for(let key of params.keys()){
        // TODO::Invoke clear cart
        if(key === 'Success'){
            CartAction.clearStorage();
            updateCartListUI();
            displayTotal();

            this.setTimeout(()=>{
                location.search = "";
            }, 3000)
        }
    }

    // get close btn
    const cancelBtn = this.document.querySelectorAll(".close");
    if(cancelBtn!=null){
        cancelBtn.forEach((selected,index)=>{
            selected.addEventListener("click",e=>{
                /**
                 * @type {HTMLSpanElement}
                 */
                const closeBtn = e.target;

                const id = closeBtn.dataset['id'];

                console.log(id);

                const rootParent = closeBtn.parentElement.parentElement.parentElement;
                CartAction.removeCartItem(id);

                rootParent.remove();

                changeBadge();
                itemsNumberEl.textContent = `${cartSize()} items`;
                displayTotal();

            })

        });
        
    }

    /**
     * @type {HTMLFormElement}
     */
    const checkoutFormEl = this.document.getElementById("checkout-form");

    const cartItems = CartAction.getLocalCartItem();

    let counter = 0;
    for(let [key, value] of cartItems.entries()){
        console.log(key, value);
        const inputQtyForm = document.createElement('input');
        const inputProductForm = document.createElement('input');

        inputProductForm.type = 'hidden';
        inputQtyForm.type = 'hidden';

        inputProductForm.name = `temporalCustomerOrderDtos[${counter}].productId`;
        inputProductForm.value = key;

        inputQtyForm.name = `temporalCustomerOrderDtos[${counter}].productQuantity`;
        inputQtyForm.value = value.quantity;

        checkoutFormEl.append(inputProductForm);
        checkoutFormEl.append(inputQtyForm);

        counter++;
    }

  }

});


function changeBadge() {
     /**
 * @type {HTMLSpanElement}
 */
  const badgeEl = document.getElementById("badge");
  badgeEl.textContent = cartSize();
}


function cartSize() {
    const cartItems = CartAction.getLocalCartItem();

    return cartItems.size;
}

function displayTotal() {
    const totalCostEl = document.getElementById('total-cost');

    totalCostEl.innerText=`K${CartAction.totalPrice()}`;
}


/**
 * 
 * @param {MouseEvent} ev 
 */

function addItemToCart(ev) {
    /**
     * @type {HTMLButtonElement}
     */
    const buttonElement = ev.target;

    const siblings = buttonElement.parentElement.childNodes;

    console.log(siblings);

    const productName = siblings[1].innerText;
    const quantity = siblings[5].children[1].value;
    const price = siblings[3].innerText.replace('K', '')

    const id = buttonElement.dataset["id"];

    const cartItem = new CartItem(productName, +quantity, price);

    CartAction.addToCart(id, cartItem);

    changeBadge();
}

class CartItem {
    constructor(name, quantity, price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}

class CartAction {
    static cartKey = "CART-ITEMS"

    /**
     * @param {string} id
     * @param {CartItem} cartItem
     */
    static addToCart(id, cartItem) {

        const cartItems = this.getLocalCartItem();
        //validate
        if (cartItems.has(id)) {
            let mapItem = cartItems.get(id);
            mapItem.quantity = +mapItem.quantity + 1;

            //update the map
            cartItems.set(id, mapItem);
        } else {
            cartItems.set(id, cartItem);
            // localStorage.setItem(this.cartKey, JSON.stringify(Object.fromEntries(cartItems)));
        }
        localStorage.setItem(this.cartKey, JSON.stringify(Object.fromEntries(cartItems)));
    };

    /**
     * @returns {Map<string, CartItem>}
     */
    static getLocalCartItem() {
        //get items from local storage
        const cartItems = localStorage.getItem(this.cartKey);

        const cartMap = new Map();
        //validate the results
        if (cartItems === null || cartItems.length === 0) {
            return cartMap;
        } else {
            return new Map(Object.entries(JSON.parse(cartItems)));
        }
    };


    static removeCartItem(id) {
        const cart = this.getLocalCartItem();
        if (cart.has(id)) {
            cart.delete(id);
        };

        if (cart.length === 0) {
            localStorage.clear();
        } else {
            localStorage.setItem(this.cartKey, JSON.stringify(Object.fromEntries(cart)));
        };
    };

    static totalPrice() {
        const cartItem = this.getLocalCartItem();

        let total = 0;
        for (const [key, value] of cartItem.entries()){
            total += +value.price * value.quantity;
        }

        return total;
    }

    static clearStorage() {
        localStorage.clear();
    }
}



// update cart List UI
function updateCartListUI(){
    /**
     * @type {HTMLDivElement}
     */
    const cartListContainer = document.getElementById("cart-List");
    cartListContainer.innerHTML = '';
    let total = 0;
    let count = 0;

    // get all data stored in local storage
    const cartListData = CartAction.getLocalCartItem();
    if(cartListData===null){
        // TODO::code an empty status message
        const cardElemnt=document.createElement('div');

        cardElemnt.innerHTML = `<p class="text-center"> no items in cart </p>`
    }else{
        for(const[key,value] of cartListData.entries()){
             //create card
             const cardElemnt=document.createElement('div');
             cardElemnt.classList.add('row', 'border-top', 'border-bottom');
 
            //  let calculatedPrice=Math.round((value.price*value.quantity)*100)/100;
             count=count+1;
            //  total=total+Math.round(calculatedPrice*100)/100;
             
             cardElemnt.innerHTML =`
             
             <div class="row main align-items-center">
                 <div class="custom-col-2 col-2"><img class="img-fluid" src="/images/gen_pic.png"></div>
                 <div class="custom-col col">
                     <div class="row text-muted">Name</div>
                     <div class="row">${value.name}</div>
                 </div>
                 <div class="custom-col col">
                     <div class="row text-muted">Quantity</div>
                     <div class="row">${value.quantity}</div>
                 </div>
                 <div class="custom-col col d-flex justify-between align-items-center">
                     <p class="mb-0">K ${+value.price * value.quantity} </p> <span data-id="${key}" class="close" style="color: #fff; background-color: #f00; padding: 10px; border-radius: 40%">&#10005;</span>
                 </div>
             </div>
         
             `;
 
             cartListContainer.append(cardElemnt);

        };
        

    };

};


