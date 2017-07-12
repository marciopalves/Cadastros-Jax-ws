import javax.xml.ws.Endpoint;

import service.FuncionarioServico;

public class Application {

	public static void main(String[] args) {
		System.out.println("Iniciando o servidor");
		Endpoint.publish("http://localhost:9999/funcionario",
				new FuncionarioServico());

	}

}
