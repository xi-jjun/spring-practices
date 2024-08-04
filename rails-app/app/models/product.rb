class Product < ApplicationRecord
  after_save :refresh_cache

  module Enable
    OFF = 0
    ON = 1
  end

  module CacheInfo
    FETCH_HASH_BY_ID = {
      key: 'product_h_%{product_id}'
    }
  end

  def self.fetch_hash_by_id(product_id)
    cache_key = cache_key_for_fetch_by_id(product_id)
    Rails.cache.read(cache_key).to_h
  end

  def set_enable
    self.enable = Enable::ON
  end

  def set_disable
    self.enable = Enable::OFF
  end

  # 현재 제품이 구매가능한지에 대한 정보를 반환하는 메서드
  #
  # @return [Boolean] 구매가능 여부 true or false
  def can_purchase?
    return false unless enable
    return false unless stock > 0

    true
  end

  private

  def refresh_cache
    cache_key = self.class.cache_key_for_fetch_by_id(id)
    product_hash = self.attributes.transform_keys(&:to_sym)
    Rails.cache.write(cache_key, product_hash)
  end

  def self.cache_key_for_fetch_by_id(product_id)
    CacheInfo::FETCH_HASH_BY_ID[:key] % { product_id: product_id }
  end
end
