require File.join(File.dirname(File.path(__FILE__)), "uf")

file = File.open("tinyUF.txt")
n = file.readline.to_i

uf = UF.new(n)
while(!file.eof)
  pair = file.readline.split(" ")
  p = pair.first.to_i
  q = pair.last.to_i
  if !uf.connected(p, q)
    uf.union(p, q)
    puts [p, q].join(" ")
  end
end
