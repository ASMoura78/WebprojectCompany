document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.querySelector('form');

    loginForm.addEventListener('submit', (event) => {
        event.preventDefault();
        const usuario = loginForm.querySelector('input[type="text"]').value;
        const senha = loginForm.querySelector('input[type="password"]').value;

        if (usuario === 'admin' && senha === '1234') { // Exemplo de validação simples
            window.location.href = 'menu.html';
        } else {
            alert('Usuário ou senha incorretos!');
        }
    });
});


