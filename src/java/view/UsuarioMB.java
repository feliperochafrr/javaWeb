/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Usuario;

/**
 *
 * @author Administrador
 */
@Named(value = "usuarioMB")
@RequestScoped
public class UsuarioMB {

    /**
     * Creates a new instance of UsuarioMB
     */
    EntityManager em;
    private Usuario usuario = new Usuario();

    public UsuarioMB() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Jpa01AbrPU");
        em = emf.createEntityManager();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String salvar() {

        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
        return "listaUsuario";
    }

    public List<Usuario> findAll() {

        Query query = em.createQuery("select u from Usuario u");
        return query.getResultList();
    }

    public String apaga(Usuario usuario) {

        System.out.println("Vou apagar o usuario " + usuario.getNome() + " id=" + usuario.getId());
        em.getTransaction().begin();
        em.remove(usuario);
        em.getTransaction().commit();
        return "listaUsuario";
    }

    public String cadastro() {
        return "cadastroUsuario";
    }
}
