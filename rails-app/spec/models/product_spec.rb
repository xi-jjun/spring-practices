require 'rails_helper'

RSpec.describe Product, type: :model do
  describe '.can_purchase?' do
    context '상품이 구매가능한 상태일 경우' do
      it 'true를 반환한다.' do
        product = Product.create!(name: 'test', price: 1000, stock: 1, enable: true)
        result = product.can_purchase?

        expect(result).to be_truthy
      end
    end

    context '상품이 재고가 0개인 상태일 경우' do
      it 'false를 반환한다.' do
        product = Product.create!(name: 'test', price: 1000, stock: 0, enable: true)
        result = product.can_purchase?

        expect(result).to be_falsey
      end
    end

    context '상품이 판매상태가 아닌 경우' do
      it 'false를 반환한다.' do
        product = Product.create!(name: 'test', price: 1000, stock: 1, enable: false)
        result = product.can_purchase?

        expect(result).to be_falsey
      end
    end
  end

  describe '.set_enable' do
    context '상품을 판매 상태로 변경했을 때' do
      it '판매가능 상태로 변경된다.' do
        product = Product.create!(name: 'test', price: 1000, stock: 1, enable: false)
        product.set_enable

        expect(product.enable).to be_falsey
      end
    end
  end

  describe '.set_disable' do
    context '상품을 판매불가 상태로 변경했을 때' do
      it '판매불가 상태로 변경된다.' do
        product = Product.create!(name: 'test', price: 1000, stock: 1, enable: true)
        product.set_disable

        expect(product.enable).to be_falsey
      end
    end
  end
end
