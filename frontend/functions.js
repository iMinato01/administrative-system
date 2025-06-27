document.addEventListener('DOMContentLoaded', () => {

  function toggleHidden(id) {
    const el = document.getElementById(id);
    el.classList.toggle('hidden');
  }

  function showElement(id) {
    const el = document.getElementById(id);
    el.classList.remove('hidden');
  }

  function hideElement(id) {
    const el = document.getElementById(id);
    el.classList.add('hidden');
  }

  // Filtros
  document.getElementById('filter-button')?.addEventListener('click', () => toggleHidden("contract-filter"));
  //Modales
    document.getElementById('contract-open-add')?.addEventListener('click', () => showElement("contract-modal-add"));
    document.getElementById('contract-close-modal-add')?.addEventListener('click', () => hideElement("contract-modal-add"));
    document.getElementById('contract-open-edit')?.addEventListener('click', () => showElement("contract-modal-edit"));
    document.getElementById('contract-close-modal-edit')?.addEventListener('click', () => hideElement("contract-modal-edit"));
    document.getElementById("open-deactivate").addEventListener("click", () => showElement("contract-modal-deactivate"));
    document.getElementById("contract-close-modal-deactivate").addEventListener("click", () => hideElement("contract-modal-deactivate"));
    document.getElementById("open-activate").addEventListener("click", () => showElement("contract-modal-activate"));
    document.getElementById("contract-close-modal-activate").addEventListener("click", () => hideElement("contract-modal-activate"));
});
