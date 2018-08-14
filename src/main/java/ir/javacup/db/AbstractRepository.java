package ir.javacup.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

public abstract class AbstractRepository<U, T extends IEntity<U>> implements IRepository<U, T> {
	private Map<IdGenerator, T> data = new HashMap<>();
	private IdGenerator<U> idGenerator;

	@Override
	public void save(T entity) {
		entity.setId(idGenerator.generate());
		if (entity == null)
			throw new IllegalArgumentException("object is null");
		else {

			data.put(idGenerator, entity);

		}

	}

	
	public T load(U id){
		if(data.containsKey(id)){
			return data.get(id);
		}
		else{
			throw new IllegalArgumentException();
		}
	}
	public List<T> loadAll(){
		List<T> list=(List<T>) data.values();
		return list;
		
	}
	
	public void delete(U id){
		if(data.containsKey(id)){
			data.remove(id);
			
		}
		else{
			throw new RuntimeException( "No match id");
		}
	}
	public void deleteAll(){
		data.clear();
	}
	public void update(T entity){
		IdGenerator key = null;
		 for (IdGenerator o : data.keySet()) {
             if (data.get(o).equals(entity)) {
            	 key=o;
               
             }
	}
		 if(key==null){
			 
		 }
		 data.put(key, entity);
	
	}
}
