$(function() {
let url = window.location.pathname;
if(url==="/blockChainBank/frontend/index.html")
{
    document.getElementById("home").style.color = "#fff";
    document.getElementById("home").style.textDecoration = "none";
}
else if(url==="/blockChainBank/frontend/blockchain.html")
{
    document.getElementById("blockchain").style.color = "#fff";
    document.getElementById("blockchain").style.textDecoration = "none";
}
else
{
    document.getElementById("login").style.color = "#fff";
    document.getElementById("login").style.textDecoration = "none";
}
});