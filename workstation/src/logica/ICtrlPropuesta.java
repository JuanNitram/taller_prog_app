package logica;

import java.util.Date;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import dataTypes.DtCategoria;
import dataTypes.DtColaboracion;
import dataTypes.DtColaborador;
import dataTypes.DtPropuesta;
import dataTypes.DtComentario;
import dataTypes.TEstado;
import dataTypes.TRetorno;
import dataTypes.TTarjeta;

public interface ICtrlPropuesta {

	boolean existePropuesta(String nickName, String titulo);
	
	void altaPropuesta(String nickName, String titulo, DtCategoria categoria, String descripcion, String lugar, Date fechaRealizacion,
			float montoReunir, TRetorno tipoRetorno, float precioEntrada, String rutaImg);

	void modificarPropuesta(String titulo, String descripcion, String lugar, Date fechaRealizacion,
			float montoReunir, float precioEntrada);
	
	List<DtPropuesta> listarPropuestas(); 

	List<DtColaborador> listarColaboradores();

	List<DtColaboracion> listarColaboraciones();
	
	List<String> listarFavoritos(String titulo);
	
	void agregarUsuarioFavorito(String nickname, String propuesta);
	
	void eliminarUsuarioFavorito(String nickname, String propuesta);
	
	DtPropuesta infoPropuesta(String titulo) throws NullPointerException;
	
	DtColaboracion infoColaboracion(int idColaboracion) throws NullPointerException;
	
	void agregarColaboracion(String nickname, float monto, TRetorno retorno);
	
	void cancelarColaboracion();
	
	void finalizarRegistrarColaboracionPropuesta();
	
	void finalizarCancelarColaboracionPropuesta();

	void crearCategoria(TreePath ruta, String nombreCat);

	DefaultMutableTreeNode listarCategorias();
	
	List<String> listarEstados();
	
	List<DtPropuesta> listarPropuestaPorEstado(TEstado estado);

	List<DtPropuesta> listarPropuestaPorCategoria(String categoria);

	void evaluar(String evaluacion);
	
	boolean extenderFinanciacion(String titulo);

	void cancelarPropuesta(String titulo);
	
	void agregarComentario(String nickname, String titulo, String comentario);
	
	List<DtComentario> listarComentarios(String titulo);
	
	void pagarColabPayPal(float monto, String nombreTitular, String nroCuenta);
	
	void pagarColabTarjeta(float monto, String nombreTitular, String numero, TTarjeta tipo, Date vencimiento, String cvc);
	
	void pagarColabTransferencia(float monto, String nombreTitular, String nomBanco, String nroCuenta);
}
