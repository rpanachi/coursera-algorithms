require File.join(File.dirname(File.path(__FILE__)), "uf")

class QuickFindUF < UF

  def connected(p, q)
    id[p] == id[q]
  end

  def union(p, q)
    pid = id[p]
    qid = id[q]
    id.size.times do |i|
      if (id[i] == pid)
        id[i] = qid
      end
    end
  end

end
