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
function showAlert(message, type = "info") {

    const alertBox = document.getElementById("customAlert");
    const alertMsg = document.getElementById("alertMessage");
    const alertIcon = document.getElementById("alertIcon");

    alertMsg.textContent = message;

    if(type === "success"){
        alertBox.className = "fixed top-6 right-6 bg-green-100 border border-green-400 text-green-700 shadow-lg rounded-lg px-6 py-4 flex items-center gap-3 z-50";
        alertIcon.textContent = "✔";
    }
    else if(type === "error"){
        alertBox.className = "fixed top-6 right-6 bg-red-100 border border-red-400 text-red-700 shadow-lg rounded-lg px-6 py-4 flex items-center gap-3 z-50";
        alertIcon.textContent = "✖";
    }
    else{
        alertBox.className = "fixed top-6 right-6 bg-blue-100 border border-blue-400 text-blue-700 shadow-lg rounded-lg px-6 py-4 flex items-center gap-3 z-50";
        alertIcon.textContent = "ℹ";
    }

    alertBox.classList.remove("hidden");

    setTimeout(()=>{
        alertBox.classList.add("hidden");
    },3000);
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