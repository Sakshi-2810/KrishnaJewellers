const ApiService = {
    // Utility for fetch
    async request(endpoint, method = 'GET', body = null) {
        const options = {
            method,
            headers: { 'Content-Type': 'application/json' }
        };
        if (body) options.body = JSON.stringify(body);
        
        const response = await fetch(`${endpoint}`, options);
        return await response.json(); // Returns your Response.java object
    },

    // Customer APIs
    getAllCustomers: () => ApiService.request('/customer/all'),
    saveCustomer: (data) => ApiService.request('/customer', 'POST', data),
    deleteCustomer: (id) => ApiService.request(`/customer?id=${id}`, 'DELETE'),

    // Mortgage APIs
    getAllMortgages: () => ApiService.request('/morgate/all'),
    saveMortgage: (data) => ApiService.request('/morgate', 'POST', data),
    deleteMortgage: (id) => ApiService.request(`/morgate?id=${id}`, 'DELETE'),
    getMortgageById: (id) => ApiService.request(`/morgate?id=${id}`),
    settleMortgage: (id) => ApiService.request(`/morgate/close?id=${id}`, 'POST'),
    getActiveMortgages: () => ApiService.request('/morgate/active'),
    getMorgateByCustomerId: (customerId) => ApiService.request(`/morgate/customer?customerId=${customerId}`)
};

/* =========================
SIDEBAR TOGGLE
========================= */

function toggleSidebar(){

const sidebar = document.querySelector(".sidebar");
const overlay = document.querySelector(".sidebar-overlay");

sidebar.classList.toggle("open");

overlay.style.display =
sidebar.classList.contains("open") ? "block" : "none";

}

function closeSidebar(){

document.querySelector(".sidebar").classList.remove("open");
document.querySelector(".sidebar-overlay").style.display="none";

}


/* =========================
LOAD SIDEBAR
========================= */


function loadSidebar(){

const sidebarHtml = `

<div class="sidebar-inner">

<!-- LOGO / BRAND -->

<div class="brand">

<h2 class="brand-title">
Krishna Jewelers
</h2>


</div>


<!-- NAVIGATION -->

<nav class="menu">

<a href="index.html" class="menu-item">

<span class="icon">📊</span>

<div class="menu-text">
Dashboard
<span>डैशबोर्ड</span>
</div>

</a>


<a href="morgate.html" class="menu-item">

<span class="icon">➕</span>

<div class="menu-text">
New Mortgage
<span>नई गिरवी</span>
</div>

</a>


<a href="mortgage-list.html" class="menu-item">

<span class="icon">📜</span>

<div class="menu-text">
Mortgage Records
<span>गिरवी रजिस्टर</span>
</div>

</a>


<a href="customer.html" class="menu-item">

<span class="icon">👤</span>

<div class="menu-text">
Customers
<span>ग्राहक सूची</span>
</div>

</a>

</nav>

</div>

`;

document.getElementById("sidebar-container").innerHTML = sidebarHtml;


/* ACTIVE MENU */

const current = window.location.pathname.split("/").pop();

document.querySelectorAll(".menu-item").forEach(link=>{

if(link.getAttribute("href") === current){
link.classList.add("active");
}

});

}