class UF

  attr_reader :id

  def initialize(size)
    @id = size.times.map(&:to_i)
  end

  def connected(p, q)
  end

  def union(p, q)
  end

end
