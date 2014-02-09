require File.join(File.dirname(File.path(__FILE__)), "uf")

class WeightedQuickUnion < UF

  attr_reader :sz

  def initialize(size)
    super
    @sz = size.times.map(&:to_i)
  end

  def connected(p, q)
    root(p) == root(q)
  end

  def union(p, q)
    i = root(p)
    j = root(q)
    return if i == j
    if (sz[i] < sz[j])
      id[i] = j
      sz[j] += sz[i]
    else
      id[j] = i
      sz[i] += sz[j]
    end
  end

  private

  def root(i)
    while(i != id[i]) do
      id[i] = id[id[i]]
      i = id[i]
    end
    i
  end

end
