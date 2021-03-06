
import java.util.*;

/**
 * A class for Symbol Table
 */
public class SymTable {

    private List<Map<String,SymInfo>> bindings;

    /**
     * Create a Symbol Table with one empty scope
     */
    public SymTable() {
        bindings = new ArrayList<Map<String,SymInfo>>();
        bindings.add(new HashMap<String,SymInfo>());
    }

    /**
     * Add a declaration (i.e. a pair [name,info]) in the inner scope
     */
    public void addDecl(String name, SymInfo info) {
        if ( name == null || info == null )
            throw new NullPointerException();
        if ( bindings.isEmpty() )
            throw new EmptySymTableException();
        Map<String,SymInfo> map = bindings.get(0);
        if ( map.get(name) != null )
            throw new DuplicateSymException();
        map.put(name, info);
    }

    /**
     * Add a new inner scope
     */
    public void addScope() {
        bindings.add(0, new HashMap<String,SymInfo>());
    }

    /**
     * Lookup for 'name' in the inner scope
     */
    public SymInfo lookupLocal(String name) {
        if ( bindings.isEmpty() )
            throw new EmptySymTableException();
        return bindings.get(0).get(name);
    }

    /**
     * Lookup for 'name' sequentially in all scopes from inner to outer
     */
    public SymInfo lookupGlobal(String name) {
        if ( bindings.isEmpty() )
            throw new EmptySymTableException();
        for ( Map<String,SymInfo> map : bindings )  {
            SymInfo info = map.get(name);
            if ( info != null )
                return info;
        }
        return null;
    }

    /**
     * Remove the inner scope
     */
    public void removeScope() {
        if ( bindings.isEmpty() )
            throw new EmptySymTableException();
        bindings.remove(0);
    }
	public int size(){
		return bindings.size();
	}
    /**
     * Print the Symbol Table on System.out
     */
    public void print() {
        System.out.print("\nSym Table\n");
        for ( Map<String,SymInfo> map : bindings )
            System.out.println(map);
        System.out.println();
    }
}
