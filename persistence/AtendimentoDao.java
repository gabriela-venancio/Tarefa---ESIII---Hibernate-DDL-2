package persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import model.Atendente;
import model.Atendimento;
import model.Cliente;

public class AtendimentoDao implements IAtendimentoDAO {
	
	private SessionFactory sf;
	
	public AtendimentoDao(SessionFactory sf) {
		this.sf = sf; 
	}
	
	@Override
	public void insere(Atendimento atend) {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(atend);
		transaction.commit();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Atendimento selectOne(Atendimento atend) {
		StringBuffer buffer = new StringBuffer();
		buffer = baseQuery();
		buffer.append("WHERE atend.cpf_cliente = " + atend.getCliente().getCpf());
		buffer.append(" AND atend.id_atendente = " + atend.getAtendente().getId());
		buffer.append(" AND atend.dataHora = " + atend.getDataHora());
		
		EntityManager entityManager = sf.createEntityManager();
		Query query = entityManager.createNativeQuery(buffer.toString());
		List<Object[]> resultList = query.getResultList();
		Object[] result = resultList.get(0);
		
		if (result != null) {
			Cliente cliente = new Cliente();
			cliente.setCpf(result[0].toString());
			cliente.setNome(result[4].toString());
			cliente.setCelular(result[5].toString());
			cliente.setEmail(result[6].toString());
			cliente.setPronomeTratamento(result[7].toString());
			
			atend.setDataHora(LocalDateTime.parse(result[2].toString()));
			
			Atendente atendente = new Atendente();
			atendente.setId(Integer.parseInt(result[1].toString()));
			atendente.setNome(result[9].toString());
			atendente.setDataNascimento(LocalDate.parse(result[10].toString()));
			atendente.setSalario(Float.parseFloat(result[11].toString()));
			atendente.setTelefone(result[12].toString());
			atendente.setHoraEntrada(Integer.parseInt(result[13].toString()));
			atendente.setHoraSaida(Integer.parseInt(result[14].toString()));
			atendente.setEmail(result[15].toString());
			
			atend.setCliente(cliente);
			atend.setAtendente(atendente);
		}
		
		return atend;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Atendimento> selectOneCliente(Atendimento atend) {
		List<Atendimento> atendimentos = new ArrayList<Atendimento>();
		StringBuffer buffer = new StringBuffer();
		buffer = baseQuery();
		buffer.append("WHERE atend.cpf_cliente = " + atend.getCliente().getCpf());
		
		EntityManager entityManager = sf.createEntityManager();
		Query query = entityManager.createNativeQuery(buffer.toString());
		List<Object[]> resultList = query.getResultList();
		
		for (Object[] result : resultList) {
			Cliente cliente = new Cliente();
			cliente.setCpf(result[0].toString());
			cliente.setNome(result[4].toString());
			cliente.setCelular(result[5].toString());
			cliente.setEmail(result[6].toString());
			cliente.setPronomeTratamento(result[7].toString());
			
			atend.setDataHora(LocalDateTime.parse(result[2].toString()));
			
			Atendente atendente = new Atendente();
			atendente.setId(Integer.parseInt(result[1].toString()));
			atendente.setNome(result[9].toString());
			atendente.setDataNascimento(LocalDate.parse(result[10].toString()));
			atendente.setSalario(Float.parseFloat(result[11].toString()));
			atendente.setTelefone(result[12].toString());
			atendente.setHoraEntrada(Integer.parseInt(result[13].toString()));
			atendente.setHoraSaida(Integer.parseInt(result[14].toString()));
			atendente.setEmail(result[15].toString());
			
			atend.setCliente(cliente);
			atend.setAtendente(atendente);
			
			atendimentos.add(atend);
		}
		
		return atendimentos;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Atendimento> selectOneAtendente(Atendimento atend) {
		List<Atendimento> atendimentos = new ArrayList<Atendimento>();
		StringBuffer buffer = new StringBuffer();
		buffer = baseQuery();
		buffer.append("WHERE atend.id_atendente = " + atend.getAtendente().getId());
		
		EntityManager entityManager = sf.createEntityManager();
		Query query = entityManager.createNativeQuery(buffer.toString());
		List<Object[]> resultList = query.getResultList();
		
		for (Object[] result : resultList) {
			Cliente cliente = new Cliente();
			cliente.setCpf(result[0].toString());
			cliente.setNome(result[4].toString());
			cliente.setCelular(result[5].toString());
			cliente.setEmail(result[6].toString());
			cliente.setPronomeTratamento(result[7].toString());
			
			atend.setDataHora(LocalDateTime.parse(result[2].toString()));
			
			Atendente atendente = new Atendente();
			atendente.setId(Integer.parseInt(result[1].toString()));
			atendente.setNome(result[9].toString());
			atendente.setDataNascimento(LocalDate.parse(result[10].toString()));
			atendente.setSalario(Float.parseFloat(result[11].toString()));
			atendente.setTelefone(result[12].toString());
			atendente.setHoraEntrada(Integer.parseInt(result[13].toString()));
			atendente.setHoraSaida(Integer.parseInt(result[14].toString()));
			atendente.setEmail(result[15].toString());
			
			atend.setCliente(cliente);
			atend.setAtendente(atendente);
			
			atendimentos.add(atend);
		}
		
		return atendimentos;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Atendimento> selectAll() {
		List<Atendimento> atendimentos = new ArrayList<Atendimento>();
		StringBuffer buffer = new StringBuffer();
		buffer = baseQuery();
		
		EntityManager entityManager = sf.createEntityManager();
		Query query = entityManager.createNativeQuery(buffer.toString());
		List<Object[]> resultList = query.getResultList();
		
		for (Object[] result : resultList) {
			Atendimento atend = new Atendimento();
			Cliente cliente = new Cliente();
			cliente.setCpf(result[0].toString());
			cliente.setNome(result[4].toString());
			cliente.setCelular(result[5].toString());
			cliente.setEmail(result[6].toString());
			cliente.setPronomeTratamento(result[7].toString());
			
			atend.setDataHora(LocalDateTime.parse(result[2].toString()));
			
			Atendente atendente = new Atendente();
			atendente.setId(Integer.parseInt(result[1].toString()));
			atendente.setNome(result[9].toString());
			atendente.setDataNascimento(LocalDate.parse(result[10].toString()));
			atendente.setSalario(Float.parseFloat(result[11].toString()));
			atendente.setTelefone(result[12].toString());
			atendente.setHoraEntrada(Integer.parseInt(result[13].toString()));
			atendente.setHoraSaida(Integer.parseInt(result[14].toString()));
			atendente.setEmail(result[15].toString());
			
			atend.setCliente(cliente);
			atend.setAtendente(atendente);
			
			atendimentos.add(atend);
		}
		
		return atendimentos;
	}
	
	private StringBuffer baseQuery() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT * FROM atendimento atend ");
		buffer.append("INNER JOIN atendente a on a.id = atend.id_atendente ");
		buffer.append("INNER JOIN cliente c on c.id = atend.cpf_cliente ");
		
		return buffer;
	}
}
