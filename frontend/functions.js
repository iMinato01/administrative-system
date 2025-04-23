document.addEventListener('DOMContentLoaded', () => {
    function hiddenElement(value) {
        const element = document.getElementById(value);
        element.classList.toggle('hidden');
    }

    document.getElementById('filter-button').addEventListener('click', () => hiddenElement("contract-filter"));
});