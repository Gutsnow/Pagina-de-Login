document.getElementById("form1").addEventListener("submit", async function (e) {
    e.preventDefault(); // Impede o envio padrão do form
  
    const email = document.getElementById("email").value;
    const senha = document.getElementById("senha").value;
  
    try {
      const resposta = await fetch("http://localhost:8080/api/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({ email, senha })
      });

      const mensagem = await resposta.text()
  
      if (resposta.ok) {
        alert(mensagem);
      } else {
        alert(mensagem);
      }
    } catch (erro) {
      alert("Erro de conexão.");
    }
  });


  document.getElementById("form2").addEventListener("submit", async function (e) {
    e.preventDefault(); // Impede o envio padrão do form

    const emailNovo = document.getElementById("emailn").value;
    const senhaNova = document.getElementById("psn").value;
    const senhaNova2 = document.getElementById("psn2").value;

    if(senhaNova == senhaNova2 && senhaNova.length > 7){

    try {
        const resposta = await fetch("http://localhost:8080/api/login/save", {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify({ email: emailNovo, senha: senhaNova })
        });

        const mensagem = await resposta.text()
    
        if (resposta.ok) {
          alert(mensagem);
        } else {
          alert(mensagem);
        }
      } catch (erro) {
        alert("Erro de conexão.");
      }
    }
    else if(senhaNova != senhaNova2){
        alert("As senhas não coincidem.");

        let caixasInput = document.querySelectorAll(".ps");
  caixasInput.forEach(input => {
    input.classList.replace("ps", "input-erro");
  });
      }
      else{
        alert("a senha deve ter mais de 8 digitos ou caracteres");
        let caixasInput = document.querySelectorAll(".ps");
        caixasInput.forEach(input => {
          input.classList.replace("ps", "input-erro");
        });
      }
    });

  

  