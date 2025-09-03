document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.getElementById('login-form');
    const errorMessageDiv = document.getElementById('login-error-message');

    // Verifica se os elementos existem antes de adicionar o listener
    if (loginForm && errorMessageDiv) {
        loginForm.addEventListener('submit', async (event) => {
            // 1. Previne o comportamento padrão do formulário (recarregar a página)
            event.preventDefault();

            // Esconde mensagens de erro anteriores
            errorMessageDiv.classList.add('d-none');
            errorMessageDiv.textContent = '';

            // 2. Pega os dados do formulário
            const formData = new FormData(loginForm);
            const data = Object.fromEntries(formData.entries());

            try {
                // 3. Envia a requisição para o backend
                // A URL deve ser a mesma do 'loginProcessingUrl' no seu SecurityConfig
                const response = await fetch('/login', { 
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(data)
                });

                // 4. Analisa a resposta
                const result = await response.json();

                if (response.ok) { // Status 200-299 indica sucesso
                    // Sucesso: redireciona para a página principal ou outra página
                    window.location.href = result.redirectUrl;
                } else {
                    // Erro: exibe a mensagem de erro retornada pelo backend
                    errorMessageDiv.textContent = result.error;
                    errorMessageDiv.classList.remove('d-none'); // Mostra o alerta de erro
                }

            } catch (error) {
                // Erro de rede ou outro problema de comunicação
                console.error('Erro ao tentar fazer login:', error);
                errorMessageDiv.textContent = 'Ocorreu um erro de comunicação. Tente novamente mais tarde.';
                errorMessageDiv.classList.remove('d-none');
            }
        });
    }
});
