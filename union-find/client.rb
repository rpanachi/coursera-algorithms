target = ARGV[0] || raise("usage: client [quick_find|quick_union]")

require File.join(File.dirname(File.path(__FILE__)), target)
require "benchmark"

file = File.open("numbers.txt")
n = file.readline.to_i

clazz = target.split("_").map(&:capitalize).join
uf = eval("#{clazz}.new(#{n})")

puts "Processing #{clazz}"

Benchmark.bm do |x|
  x.report do
    while(!file.eof)
      pair = file.readline.split(" ")
      p = pair.first.to_i
      q = pair.last.to_i
      if !uf.connected(p, q)
        uf.union(p, q)
      end
    end
  end
end
