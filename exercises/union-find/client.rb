target  = ARGV[0] || raise("usage: client [quick_find|quick_union|weighted_quick_union] file.txt [true|false]")
numbers = ARGV[1] || "numbers.txt"
@verbose = ARGV[2] || false

require File.join(File.dirname(File.path(__FILE__)), target)
require "benchmark"

def output(array)
  return unless @verbose
  print "\nid = ["
  print array.join(" ")
  print("]\n\n")
end

file = File.open(numbers)
n = file.readline.to_i

clazz = target.split("_").map(&:capitalize).join
uf = eval("#{clazz}.new(#{n})")

puts "Processing #{clazz}"

Benchmark.bm do |x|
  x.report do
    output(uf.id)
    while(!file.eof)
      pair = file.readline.split(" ")
      p = pair.first.to_i
      q = pair.last.to_i
      if !uf.connected(p, q)
        puts "union(#{p}, #{q})" if @verbose
        uf.union(p, q)
        output(uf.id)
      end
    end
  end
end
