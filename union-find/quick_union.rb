require File.join(File.dirname(File.path(__FILE__)), "uf")

class QuickUnion < UF

  def connected(p, q)
    root(p) == root(q)
  end

  def union(p, q)
    i = root(p)
    j = root(q)
    id[i] = j
  end

  private

  def root(i)
    while(i != id[i]) do
      i = id[i]
    end
    i
  end

end
