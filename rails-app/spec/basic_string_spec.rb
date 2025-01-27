# require 'rails_helper'

RSpec.describe 'BasicString' do
  describe 'Sting concat' do
    context 'When merge two string "a" and "b" by "+"' do
      it 'return the "ab" string.' do
        str1 = "a"
        str2 = "b"
        expect(str1 + str2).to eq("ab")
      end

      it 'return ab string 2' do
        str1 = "a"
        str2 = "b"
        expect(str1 + str2).to eq("abb")
      end
    end
  end
end
