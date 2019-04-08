package br.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.dao.ContatoDao;
import br.com.modelo.Contato;


@WebServlet("/AdicionarContato")
public class AdicionaContato extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        
            PrintWriter out = response.getWriter();

            // pegando os parâmetros do request
            String nome = request.getParameter("nome");
            String endereco = request.getParameter("endereco");
            String email = request.getParameter("email");
            String dataEmTexto = request.getParameter("dataNascimento");
            Calendar dataNascimento = null;

            // fazendo a conversão da data

            try {
                Date date = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(dataEmTexto);      
                dataNascimento = Calendar.getInstance();
                dataNascimento.setTime(date);
            } catch (ParseException e) {
                out.println("Erro de conversão da data");
                throw new ServletException(e);
                //return; //para a execução do método
            }


            // monta um objeto contato
            Contato contato = new Contato();
            contato.setNome(nome);
            contato.setEndereco(endereco);
            contato.setEmail(email);
            contato.setDataNascimento(dataNascimento);

            // salva o contato
            ContatoDao dao = new ContatoDao();
            dao.adiciona(contato);

            // imprime o nome do contato que foi adicionado
            out.println("<html>");
            out.println("<body style=\"background-image:url('./img/matrix3.gif')\">");
            out.println("<h1 style=\"color:white\">Contato <i style=\"color:gray\" >" + contato.getNome() +
                    "</i> adicionado com sucesso</h1>" +
                    "<a href='index.html'><h2 style=\"color:white\">"
                    + "Adicionar mais contatos</h2></a>"

            );        
            out.println("</body>");
            out.println("</html>");
    }
	

}